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
		testHttp();
		testHttpsWebIdTLS();
	}
		
	public static void testHttp() {

		System.err.println("Testing HTTP");

		Client client;
		Response res;

		client = ClientBuilder.newClient();
		res = client.target(
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

	/**
	 * You need a client certificate and supply a corresponding {@link SSLContext} to the {@link ClientBuilder}.
	 * You can use Jersey's SslConfigurator to generate the {@link SSLContext} like in:
	 * 
	 * <br><br>
	 * <code>
	 * 	org.glassfish.jersey.SslConfigurator sslconfig = SslConfigurator.newInstance(true);
	 *  sslconfig.keyStoreFile("/path/to/keystore.p12");
	 *  sslconfig.keyStoreType("PKCS12");
	 *  sslconfig.keyStorePassword("t0p_s3cr3t");
	 *  SSLContext sslcontext = sslconfig.createSSLContext();
	 *  
	 *  ClientBuilder.newBuilder().sslContext(sslcontext).build()
	 * </code>
	 * <br><br>
	 * 
	 * Or set the system variables:
	 * <ul>
	 * <li><pre>javax.net.ssl.keyStore</pre></li> 
	 * <li><pre>javax.net.ssl.keyStorePassword</pre></li>
	 * <li><pre>javax.net.ssl.keyStoreType</pre></li>
	 * </ul>
	 */
	public static void testHttpsWebIdTLS() {

		System.err.println("Testing HTTPS+WebID+TLS");

		Client client;
		Response res;

		client = ClientBuilder.newBuilder().build();
		
		res = client.target(
				"https://kaefer3000.databox.me/Inbox/"
				)
				.request(
						MediaType.valueOf("text/turtle")
						).get();

		if (res.getStatus() >= 200 && res.getStatus() < 300)
			// Successful request
			for (Node[] nx : res.readEntity(new GenericType<Iterable<Node[]>>() {
			})) {
				System.out.println(Nodes.toString(nx));
			}
		else
			System.err.println("Request was not successful." + res);
		
	}

}
