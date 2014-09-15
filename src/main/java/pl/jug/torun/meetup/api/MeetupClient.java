/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.meetup.api;

import java.util.List;
import pl.jug.torun.meetup.api.model.Event;
import pl.jug.torun.meetup.api.model.Member;

/**
 *
 * @author faramir
 */
public interface MeetupClient {

    public List<Event> getEvents(String groupName);

    public List<Member> getMembers(String eventId);

}
