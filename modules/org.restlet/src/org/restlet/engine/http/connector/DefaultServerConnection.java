/**
 * Copyright 2005-2009 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.engine.http.connector;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

import org.restlet.Request;
import org.restlet.Response;

/**
 * An internal HTTP server connection.
 * 
 * @author Jerome Louvel
 */
public class DefaultServerConnection extends ServerConnection {

    /** The inbound stream. */
    private final InputStream inboundStream;

    /** The outbound stream. */
    private final OutputStream outboundStream;

    private volatile boolean persistent;

    private volatile boolean pipelining;

    private final Queue<Request> inboundRequests;

    private final Queue<Response> outboundResponses;

    /**
     * Constructor.
     * 
     * @param helper
     * @param socket
     * @throws IOException
     */
    public DefaultServerConnection(DefaultServerHelper helper, Socket socket)
            throws IOException {
        super(helper, socket);
        this.inboundStream = new BufferedInputStream(socket.getInputStream());
        this.outboundStream = new BufferedOutputStream(socket.getOutputStream());
        this.persistent = false;
        this.pipelining = false;
        this.inboundRequests = new ConcurrentLinkedQueue<Request>();
        this.outboundResponses = new ConcurrentLinkedQueue<Response>();
    }

    @Override
    public void close() {
        super.close();
    }

    /**
     * Returns the connection handler service.
     * 
     * @return The connection handler service.
     */
    protected ExecutorService getHandlerService() {
        return getHelper().getHandlerService();
    }

    @Override
    public DefaultServerHelper getHelper() {
        return (DefaultServerHelper) super.getHelper();
    }

    @Override
    public InputStream getInboundStream() {
        return this.inboundStream;
    }

    @Override
    public OutputStream getOutboundStream() {
        return this.outboundStream;
    }

    @Override
    public ReadableByteChannel getRequestEntityChannel(long size) {
        return null;
    }

    @Override
    public InputStream getRequestEntityStream(long size) {
        return null;
    }

    @Override
    public ReadableByteChannel getRequestHeadChannel() {
        return null;
    }

    @Override
    public InputStream getRequestHeadStream() {
        return getInboundStream();
    }

    public Queue<Request> getInboundRequests() {
        return inboundRequests;
    }

    @Override
    public WritableByteChannel getResponseEntityChannel() {
        return null;
    }

    @Override
    public OutputStream getResponseEntityStream() {
        return null;
    }

    public Queue<Response> getOutboundResponses() {
        return outboundResponses;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public boolean isPipelining() {
        return pipelining;
    }

    public void readRequests() {
        try {
            // Read the request on the socket
            ConnectedRequest request = readRequest();

            if (isPipelining()) {
                boolean idempotentSequence = true;

            } else {
                // Add it to the connection queue
                getInboundRequests().add(request);

                // Add it to the helper queue
                getHelper().getPendingRequests().add(request);
            }

            while (request != null) {

            }
        } catch (Exception e) {
            getLogger().log(Level.WARNING,
                    "Error while reading an HTTP request: ", e.getMessage());
            getLogger().log(Level.INFO, "Error while reading an HTTP request",
                    e);
        }
    }

    /**
     * 
     * @return
     */
    public boolean writeResponses() {
        boolean result = false;

        try {
            // Response nextResponse = getOutboundResponses().poll();

        } catch (Exception e) {
            getLogger().log(Level.WARNING,
                    "Error while writing an HTTP response: ", e.getMessage());
            getLogger().log(Level.INFO, "Error while writing an HTTP response",
                    e);
        }

        return result;
    }

    @Override
    public void open() {
        super.open();

        if (!getHandlerService().isShutdown()) {
            try {
                getHandlerService().execute(new Runnable() {
                    public void run() {
                    }
                });
                getHelper().handle(null, null);
            } catch (Exception e) {
                getLogger().log(Level.WARNING,
                        "Error while handling an HTTP server call: ",
                        e.getMessage());
                getLogger().log(Level.INFO,
                        "Error while handling an HTTP server call", e);
            }
        }
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public void setPipelining(boolean pipelining) {
        this.pipelining = pipelining;
    }

}
