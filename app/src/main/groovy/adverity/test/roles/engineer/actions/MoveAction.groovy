package adverity.test.roles.engineer.actions

import adverity.test.roles.Action
import adverity.test.roles.Role
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.hc.client5.http.fluent.Request

class MoveAction extends Action {
    MoveAction(Role role) {
        super(role)
    }

    Map cardToList(String cardId, String listId) {
        def uri = uriBuilder.setPath("/1/cards/$cardId")
            .addParameter('idList', listId)
            .build()

        def content = Request.put(uri)
                .execute()
                .returnContent()

        new ObjectMapper().readValue(content.toString(), Map)
    }
}
