package context.input.event;

import context.input.networking.NetworkSource;
import context.input.networking.packet.PacketModel;

public class PacketReceivedInputEvent extends GameInputEvent {

	private final NetworkSource source;
	private final PacketModel model;

	public PacketReceivedInputEvent(NetworkSource source, PacketModel model) {
		this.source = source;
		this.model = model;
	}

	public NetworkSource source() {
		return source;
	}

	public PacketModel model() {
		return model;
	}

}
