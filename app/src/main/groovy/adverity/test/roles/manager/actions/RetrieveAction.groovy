package adverity.test.roles.manager.actions

import adverity.test.roles.Action
import adverity.test.roles.Role
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.hc.client5.http.fluent.Request

class RetrieveAction extends Action {
    RetrieveAction(Role role) {
        super(role)
    }

    List<Map> boards() {
        def uri = uriBuilder.setPath('/1/members/me/boards')
            .build()

        def content = Request.get(uri)
                .execute()
                .returnContent()

        new ObjectMapper().readValue(content.toString(), List<Map>)
    }

    List<Map> lists(String boardId) {
        def uri = uriBuilder.setPath("/1/boards/$boardId/lists")
                .build()

        def content = Request.get(uri)
                .execute()
                .returnContent()

        new ObjectMapper().readValue(content.toString(), List<Map>)
    }

    List<Map> notifications() {
        def uri = uriBuilder.setPath('/1/members/me/notifications')
                .addParameter('read_filter', 'unread')
                .build()

        def content = Request.get(uri)
                .execute()
                .returnContent()

        new ObjectMapper().readValue(content.toString(), List<Map>)
    }
}
