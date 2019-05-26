import spock.lang.Specification

class Publisher {
    List<Subscriber> subscribers = []
    int messageCount = 0
    void send(String message){
        subscribers*.receive(message)
        messageCount++
    }
    List<String> getMessage(String message) {
        subscribers*.foo(message)
    }
}

interface Subscriber {
    void receive(String message)
    String foo(String message)
}

class PublisherSpec extends Specification {
    Publisher publisher = new Publisher()
    Subscriber subscriber = Mock()
    Subscriber subscriber2 = Mock()

    def setup() {
        publisher.subscribers << subscriber // << is a Groovy shorthand for List.add()
        publisher.subscribers << subscriber2
    }



    def "should send messages to all subscribers"() {
        when:
        publisher.send("hello")

        then:
        1 * subscriber.receive("hello")
        1 * subscriber2.receive("hello")
    }

    def "get message from stub"() {
        given:
        subscriber.foo("aa") >> "ok1"
        subscriber2.foo("aa") >> "ok2"

        when:
        def messages = publisher.getMessage("aa")

        then:
        messages == ["ok1", "ok2"]
    }
}