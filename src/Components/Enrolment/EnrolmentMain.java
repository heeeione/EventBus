package Components.Enrolment;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Framework.Event;
import Framework.EventId;
import Framework.EventQueue;
import Framework.RMIEventBus;

public class EnrolmentMain {

	public static void main(String[] args) throws IOException, RemoteException, NotBoundException {
		RMIEventBus eventBus = (RMIEventBus) Naming.lookup("EventBus");
		long componentId = eventBus.register();
		System.out.println("EnrolmentMain (ID:" + componentId + ") is successfully registered...");
		
		Event event = null;
		boolean done = false;
		String studentInfo = null, courseInfo = null;
		while (!done) {
//			try { Thread.sleep(1000); } 1초 늦추면 강의 정보 안 들어옴
//			catch (InterruptedException e) { e.printStackTrace(); }
			EventQueue eventQueue = eventBus.getEventQueue(componentId);
			for (int i = 0; i < eventQueue.getSize(); i++) {
				event = eventQueue.getEvent();
				switch (event.getEventId()) {
				case ListStudentsRegistered:
					printLogEvent("Get", event);
					eventBus.sendEvent(new Event(EventId.ClientOutput));
					studentInfo = event.getMessage();
					break;
				case ListCourseRegistered:
					printLogEvent("Get", event);
					eventBus.sendEvent(new Event(EventId.ClientOutput));
					courseInfo = event.getMessage();
					break;
				default:
					break;
				}
			if (studentInfo != null && courseInfo != null) {
				boolean checkPreCourses = true;
				String[] info = courseInfo.split(" ");
				for (int j = 0; j < info.length-3; j++)
					if (!studentInfo.contains(info[j+3])) checkPreCourses = false; // 강의 3번째 index부터 선수과목
				boolean takenCourse = studentInfo.contains(info[0]); // 선택한 강의 ID
				if (takenCourse) System.out.println("You have already taken course");
				else if (!checkPreCourses) System.out.println("You didn't take any prerequisites.");
				else System.out.println("You have successfully registered for classes.");
				studentInfo = null; courseInfo = null;
			}
			}
		}
	}

	private static void printLogEvent(String comment, Event event) {
		System.out.println(
				"\n** " + comment + " the event(ID:" + event.getEventId() + ") message: " + event.getMessage());
	}
}
