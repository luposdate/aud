package section2.queue;

import misc.InOutHelper;

public class QueueSimulation {

	public static void main(final String[] args){
		System.out.println("Programm zur Simulation von Warteschlangen...");
		System.out.println("Bitte geben Sie a für ArrayQueue ein, ansonsten wird der ListQueue verwendet:");
		final String type = InOutHelper.readString();
		final ADTQueue<String> queue = (type.compareToIgnoreCase("a")==0)? new ArrayQueue<String>() : new ArrayQueue<String>(); //new ListQueue<String>();
		while(true){
			System.out.println("Bitte geben Sie jetzt die nächste Aktion ein (q=quit, d=dequeue, any other=enqueue):");
			final String command=InOutHelper.readString();
			if(command.compareToIgnoreCase("q")==0){
				return;
			} else if(command.compareToIgnoreCase("d")==0){
				queue.dequeue();
			} else {
				queue.enqueue(command);
			}
			System.out.println("queue=\n"+queue);
		}
	}
}
