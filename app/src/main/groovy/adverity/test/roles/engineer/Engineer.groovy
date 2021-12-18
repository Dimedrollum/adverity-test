package adverity.test.roles.engineer

import adverity.test.roles.Role
import adverity.test.roles.engineer.actions.MoveAction
import adverity.test.roles.manager.actions.CreateAction
import adverity.test.roles.manager.actions.RetrieveAction

class Engineer extends Role {

    Engineer(String key, String token) {
        super.key = key
        super.token = token
    }

    MoveAction moves() {
        new MoveAction(this)
    }
}
