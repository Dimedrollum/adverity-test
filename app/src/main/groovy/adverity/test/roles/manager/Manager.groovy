package adverity.test.roles.manager

import adverity.test.roles.Role
import adverity.test.roles.manager.actions.CreateAction
import adverity.test.roles.manager.actions.RetrieveAction

class Manager extends Role {

    Manager(String key, String token) {
        super.key = key
        super.token = token
    }

    RetrieveAction retrieves() {
        new RetrieveAction(this)
    }

    CreateAction creates() {
        new CreateAction(this)
    }
}
