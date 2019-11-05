/**
 * Copyright 2005-2019 Talend
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or or EPL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */

package org.restlet.ext.jaxrs.internal.exceptions;

import javax.ws.rs.MatrixParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

/**
 * This kind of exception is thrown, if a matrix parameter could not be
 * converted.
 * 
 * @author Stephan Koops
 * @see MatrixParam
 * @deprecated Will be removed in next minor release.
 */
@Deprecated
public class ConvertMatrixParamException extends WebApplicationException {

    private static final long serialVersionUID = 6704339151884788476L;

    /**
     * @param cpe
     */
    public ConvertMatrixParamException(ConvertParameterException cpe) {
        super(cpe.getCause(), Status.NOT_FOUND);
        setStackTrace(cpe.getStackTrace());
    }
}
