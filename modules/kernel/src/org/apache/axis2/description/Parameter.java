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
package org.apache.axis2.description;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.util.ObjectStateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


/**
 * Class Parameter
 */
public class Parameter implements Externalizable {

    /*
     * setup for logging
     */
    private static final Log log = LogFactory.getLog(Parameter.class);

    private static final String myClassName = "Parameter";

    /**
     * @serial The serialization version ID tracks the version of the class.
     * If a class definition changes, then the serialization/externalization
     * of the class is affected. If a change to the class is made which is
     * not compatible with the serialization/externalization of the class,
     * then the serialization version ID should be updated.
     * Refer to the "serialVer" utility to compute a serialization
     * version ID.
     */
    private static final long serialVersionUID = -6601664200673063531L;

    /**
     * @serial Tracks the revision level of a class to identify changes to the
     * class definition that are compatible to serialization/externalization.
     * If a class definition changes, then the serialization/externalization
     * of the class is affected.
     * Refer to the writeExternal() and readExternal() methods.
     */
    // supported revision levels, add a new level to manage compatible changes
    private static final int REVISION_1 = 1;
    // current revision level of this object
    private static final int revisionID = REVISION_1;


    /**
     * Field  ANY_PARAMETER
     */
    public final static int ANY_PARAMETER = 0;
    /**
     * Field TEXT_PARAMETER
     */
    public final static int TEXT_PARAMETER = 1;

    /**
     * Field OM_PARAMETER
     */
    public final static int OM_PARAMETER = 2;

    /**
     * Field type
     */
    private int type = TEXT_PARAMETER;

    /**
     * Field locked
     */
    private boolean locked;

    /**
     * Field name
     */
    private String name;

    /**
     * to store the parameter element
     * <parameter name="ServiceClass1" locked="false">
     * org.apache.axis2.sample.echo.EchoImpl</parameter>
     */
    private OMElement parameterElement;

    /**
     * Field value
     */
    private Object value;

    /**
     * Constructor.
     */
    public Parameter() {
    }

    /**
     * Constructor from name and value.
     *
     * @param name
     * @param value
     */
    public Parameter(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Method getName.
     *
     * @return Returns String.
     */
    public String getName() {
        return name;
    }

    public OMElement getParameterElement() {
        return this.parameterElement;
    }

    /**
     * Method getParameterType.
     *
     * @return Returns int.
     */
    public int getParameterType() {
        return type;
    }

    /**
     * Method getValue.
     *
     * @return Returns Object.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Method isLocked.
     *
     * @return Returns boolean.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Method setLocked.
     *
     * @param value
     */
    public void setLocked(boolean value) {
        locked = value;
    }

    /**
     * Method setName.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setParameterElement(OMElement element) {
        this.parameterElement = element;
    }

    public void setParameterType(int type) {
        this.type = type;
    }

    /**
     * Method setValue.
     *
     * @param value
     */
    public void setValue(Object value) {
        if (value instanceof String){
            setParameterType(TEXT_PARAMETER);
        } else if (value instanceof OMElement) {
            setParameterType(OM_PARAMETER);
        } else {
            setParameterType(ANY_PARAMETER);
        }
        this.value = value;
    }

    public String toString() {
        return "Parameter : " + name + "=" + value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Parameter) {
            return ((Parameter) obj).name.equals(name);
        }
        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }

    /* ===============================================================
    * Externalizable support
    * ===============================================================
    */


    /**
     * Save the contents of this object.
     * <p/>
     * NOTE: Transient fields and static fields are not saved.
     * Also, objects that represent "static" data are
     * not saved, except for enough information to be
     * able to find matching objects when the message
     * context is re-constituted.
     *
     * @param out The stream to write the object contents to
     * @throws IOException
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        // write out contents of this object

        //---------------------------------------------------------
        // in order to handle future changes to the message
        // context definition, be sure to maintain the
        // object level identifiers
        //---------------------------------------------------------
        // serialization version ID
        out.writeLong(serialVersionUID);

        // revision ID
        out.writeInt(revisionID);

        //---------------------------------------------------------
        // simple fields
        //---------------------------------------------------------

        out.writeInt(type);
        out.writeBoolean(locked);

        ObjectStateUtils.writeString(out, name, "Parameter.name");

        //---------------------------------------------------------
        // object fields
        //---------------------------------------------------------

        // TODO: investigate serializing the OMElement more efficiently
        // This currently will basically serialize the given OMElement
        // to a String but will build the OMTree in the memory

        String tmp = null;

        if (parameterElement != null) {
            tmp = parameterElement.toString();
        }

        // treat as an object, don't do UTF
        ObjectStateUtils.writeObject(out, tmp, "Parameter.parameterElement");

        // TODO: error handling if this can't be serialized
        ObjectStateUtils.writeObject(out, value, "Parameter.value");

    }


    /**
     * Restore the contents of the object that was previously saved.
     * <p/>
     * NOTE: The field data must read back in the same order and type
     * as it was written.  Some data will need to be validated when
     * resurrected.
     *
     * @param in The stream to read the object contents from
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // trace point
        if (log.isTraceEnabled()) {
            log.trace(myClassName + ":readExternal():  BEGIN  bytes available in stream [" +
                    in.available() + "]  ");
        }

        // serialization version ID
        long suid = in.readLong();

        // revision ID
        int revID = in.readInt();

        // make sure the object data is in a version we can handle
        if (suid != serialVersionUID) {
            throw new ClassNotFoundException(ObjectStateUtils.UNSUPPORTED_SUID);
        }

        // make sure the object data is in a revision level we can handle
        if (revID != REVISION_1) {
            throw new ClassNotFoundException(ObjectStateUtils.UNSUPPORTED_REVID);
        }

        //---------------------------------------------------------
        // simple fields
        //---------------------------------------------------------

        type = in.readInt();
        locked = in.readBoolean();

        name = ObjectStateUtils.readString(in, "Parameter.name");

        //---------------------------------------------------------
        // object fields
        //---------------------------------------------------------

        // TODO: investigate serializing the OMElement more efficiently
        // This currently will basically serialize the given OMElement
        // to a String but will build the OMTree in the memory

        // treat as an object, don't do UTF
        String tmp = (String) ObjectStateUtils.readObject(in, "Parameter.parameterElement");

        // convert to an OMElement
        if (tmp != null) {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(tmp.getBytes());

                XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(bais);

                // TODO: the StAXOMBuilder is an impl class - is there a better mechanism rather than an impl class ?
                StAXOMBuilder builder = new StAXOMBuilder(OMAbstractFactory.getOMFactory(), parser);

                OMElement docElement = builder.getDocumentElement();

                if (docElement != null) {
                    parameterElement = docElement;
                } else {
                    // TODO: error handling if can't create an OMElement
                    parameterElement = null;
                }
            }
            catch (Exception exc) {
                // TODO: error handling if can't create an OMElement
                parameterElement = null;
            }
        } else {
            parameterElement = null;
        }

        // TODO: error handling if this can't be serialized
        value = ObjectStateUtils.readObject(in, "Parameter.value");

        //---------------------------------------------------------
        // done
        //---------------------------------------------------------

    }
}
