/*
* Copyright 2004,2005 The Apache Software Foundation.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.apache.axis2.transport.local;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.AbstractTransportSender;

import java.io.OutputStream;

/**
 * LocalResponder
 */
public class LocalResponder extends AbstractTransportSender {
    LocalTransportSender sender;

    public LocalResponder(LocalTransportSender sender) {
        this.sender = sender;
    }

    public OutputStream startSendWithToAddress(MessageContext msgContext, OutputStream out) throws AxisFault {
        return out;
    }

    public void finalizeSendWithToAddress(MessageContext msgContext, OutputStream out) throws AxisFault {
    }

    public OutputStream startSendWithOutputStreamFromIncomingConnection(MessageContext msgContext, OutputStream out) throws AxisFault {
        return null;  
    }

    public void finalizeSendWithOutputStreamFromIncomingConnection(MessageContext msgContext, OutputStream out) throws AxisFault {
    }

    protected OutputStream openTheConnection(EndpointReference epr, MessageContext msgctx) throws AxisFault {
        return sender.getResponse();
    }

    /**
     * Clean up
     *
     * @param msgContext
     * @throws org.apache.axis2.AxisFault
     */
    public void cleanUp(MessageContext msgContext) throws AxisFault {
    }
}
