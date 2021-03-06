/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.axis2.clustering.context.commands;

import org.apache.axis2.clustering.context.ContextClusteringCommand;
import org.apache.axis2.clustering.context.PropertyUpdater;
import org.apache.axis2.context.PropertyDifference;

import java.util.HashMap;

/**
 * 
 */
public abstract class UpdateContextCommand extends ContextClusteringCommand {

    protected PropertyUpdater propertyUpdater = new PropertyUpdater();

    public boolean isPropertiesEmpty() {
        if (propertyUpdater.getProperties() == null) {
            propertyUpdater.setProperties(new HashMap());
            return true;
        }
        return propertyUpdater.getProperties().isEmpty();
    }

    public void addProperty(PropertyDifference diff) {
        if (propertyUpdater.getProperties() == null) {
            propertyUpdater.setProperties(new HashMap());
        }                                        
        propertyUpdater.addContextProperty(diff);
    }
}
