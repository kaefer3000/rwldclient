package edu.kit.aifb.websci;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.Nodes;

public class RWLDclient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		Response res = client.target(
				"http://www.w3.org/1999/02/22-rdf-syntax-ns"
				)
				.request(
						MediaType.valueOf("application/rdf+xml")
						).get();

		if (res.getStatus() >= 200 && res.getStatus() < 300)
			// Successful request
			for (Node[] nx : res.readEntity(new GenericType<Iterable<Node[]>>() {
			})) {
				System.out.println(Nodes.toString(nx));
			}
		else
			System.err.println("Request was not successful.");
	}

}
