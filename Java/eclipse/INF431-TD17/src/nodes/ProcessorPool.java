package nodes;

import data.Message;
import data.MessageProcessor;
import data.MessageQueue;

public class ProcessorPool extends Node {
	
	private final MessageQueue queue;
	private final MessageProcessor processor;
	private int threadNumber;

	public ProcessorPool(MessageQueue q,  int threadNumber,
			MessageProcessor p, String name) {
		super(name);
		queue = q;
		processor = p;
		this.threadNumber = threadNumber;
	}
	
	@Override
	public void start() {
		for (int i =0; i< this.threadNumber; i++) {
			Thread t = new Thread(new Processor(this.queue,this.processor,this.getName() + " " + i));
			t.start();
		}
	}
	
	@Override
	public void putInQueue(Message message) {
		queue.add(message);
	}

	public void run() {	}
}
