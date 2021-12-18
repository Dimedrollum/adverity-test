package adverity.test

import adverity.test.dto.Photo
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.hc.client5.http.fluent.Request
import spock.lang.Specification

class ApiTest extends Specification {

    def 'should return 200'() {
        when:
        def response = Request.get('https://jsonplaceholder.typicode.com/photos')
                .execute()

        then:
        response.returnResponse().code == 200
    }

    def 'should be 5000 photos'() {
        given:
        listOfPhotos

        expect:
        listOfPhotos.size() == 5000
    }

    def 'should contain "non sit quo" photo'() {
        given:
        listOfPhotos

        expect:
        listOfPhotos.any { it.title == 'non sit quo' }
    }

    def 'should have 50 photos in album 100'() {
        given:
        listOfPhotos

        when:
        def filteredList = listOfPhotos.findAll { it.albumId == 100 }

        then:
        filteredList.size() == 50
    }

    def 'should have 111 photos with error'() {
        given:
        listOfPhotos

        when:
        def filteredList = listOfPhotos.findAll { it.title.contains('error') }

        then:
        filteredList.size() == 111
    }

    List<Photo> getListOfPhotos() {
        def response = Request.get('https://jsonplaceholder.typicode.com/photos')
                .execute()
                .returnContent()

        new ObjectMapper().readValue(response.toString(), List<Photo>)
    }
}
