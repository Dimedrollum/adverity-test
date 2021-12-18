package adverity.test.roles.manager.actions

import adverity.test.roles.Action
import adverity.test.roles.Role
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.hc.client5.http.fluent.Request

class CreateAction extends Action {
    CreateAction(Role role) {
        super(role)
    }

    Map card(String listId) {
        def uri = uriBuilder.setPath('/1/cards')
            .addParameter('idList', listId)
            .build()

        def content = Request.post(uri)
                .execute()
                .returnContent()

        new ObjectMapper().readValue(content.toString(), Map)
    }
}
