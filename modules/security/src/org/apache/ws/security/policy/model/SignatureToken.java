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

package org.apache.ws.security.policy.model;

import org.apache.ws.security.policy.WSSPolicyException;

public class SignatureToken extends PolicyEngineData implements TokenWrapper {

    private Token signatureToken;

    /**
     * @return Returns the signatureToken.
     */
    public Token getSignatureToken() {
        return signatureToken;
    }

    /**
     * @param signatureToken The signatureToken to set.
     */
    public void setSignatureToken(Token signatureToken) {
        this.signatureToken = signatureToken;
    }

    /* (non-Javadoc)
     * @see org.apache.ws.security.policy.TokenWrapper#setToken(org.apache.ws.security.policy.Token)
     */
    public void setToken(Token tok) throws WSSPolicyException {
        this.setSignatureToken(tok);
    }
    
    
}
