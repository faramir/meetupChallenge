package pl.jug.torun.meetup.api;

import org.springframework.web.client.RestTemplate;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pl.jug.torun.meetup.api.model.Event;
import pl.jug.torun.meetup.api.model.EventList;
import pl.jug.torun.meetup.api.model.EventMemberList;
import pl.jug.torun.meetup.api.model.Member;

public class MeetupClientImpl implements MeetupClient {

    final private MeetupPath meetupPath = new MeetupPath();

    final private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Event> getEvents(String groupName) {
        Map<String, String> params = new HashMap<>();
        params.put("group_urlname", groupName);
        params.put("status", "upcoming,past");

        String url;
        try {
            url = meetupPath.getUrl("2/events", params);
        } catch (URISyntaxException e) {
            throw new MeetupException("Meetup Url not valid", e);
        }

        System.out.println("GET " + url);
        return restTemplate.getForObject(url, EventList.class).getResults();
    }

    @Override
    public List<Member> getMembers(String eventId) {
        Map<String, String> params = new HashMap<>();
        params.put("event_id", eventId);
        params.put("rsvp", "yes");

        String url;
        try {
            url = meetupPath.getUrl("2/rsvps", params);
        } catch (URISyntaxException e) {
            throw new MeetupException("Meetup Url not valid", e);
        }

        System.out.println("GET " + url);
        return restTemplate.getForObject(url, EventMemberList.class).getResults()
                .stream()
                .map(e -> e.getMember())
                .collect(Collectors.toList());
    }

}
