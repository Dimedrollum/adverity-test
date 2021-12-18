package adverity.test

import adverity.test.roles.Cast
import spock.lang.Specification

import static adverity.test.constants.TrelloConstants.*

class TrelloSpec extends Specification {

    def 'manager checks existing board'() {
        when:
        def boards = Cast.MANAGER.retrieves().boards()

        then:
        boards.find { it.name == BOARD_NAME }
                .id == BOARD_ID
    }

    def 'manager checks lists on board'() {
        when:
        def lists = Cast.MANAGER.retrieves().lists(BOARD_ID)

        then:
        LISTS.each { expectedList ->
            assert expectedList.value == lists.find() { actualList ->
                actualList.name == expectedList.key
            }.id
        }
    }

    def 'manager creates card'() {
        when:
        def card = Cast.MANAGER.creates().card(LISTS.'ToDo')

        then:
        card != null
    }

    def 'engineer moves card to doing'() {
        given:
        String cardId = Cast.MANAGER.creates().card(LISTS.'ToDo').id

        when:
        def card = Cast.ENGINEER.moves().cardToList(cardId, LISTS.Doing)

        then:
        card.id == cardId
        card.idList == LISTS.Doing
    }

    def 'manager gets notified when card is put to doing'() {
        given:
        def initialNotificationsCount = Cast.MANAGER.retrieves().notifications().size()
        String cardId = Cast.MANAGER.creates().card(LISTS.'ToDo').id
        Cast.ENGINEER.moves().cardToList(cardId, LISTS.Doing)

        when:
        def notifications = Cast.MANAGER.retrieves().notifications()

        then:
        notifications.size() == initialNotificationsCount + 1
    }
}
