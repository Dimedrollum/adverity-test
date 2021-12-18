package adverity.test.roles

import org.apache.hc.core5.net.URIBuilder

abstract class Action {
    protected Role role
    Action(Role role){
        this.role = role
    }

    URIBuilder getUriBuilder() {
        new URIBuilder()
                .setScheme('https')
                .setHost('api.trello.com')
                .setParameter('key', role.key)
                .setParameter('token', role.token)
    }
}