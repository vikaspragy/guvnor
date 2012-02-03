/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.testframework.populators;

import java.util.Map;

import org.drools.base.TypeResolver;
import org.drools.ide.common.client.modeldriven.testing.FactData;

public class FactPopulatorFactory {

    private Map<String, Object> populatedData;
    private Map<String, Object> globalData;
    private final TypeResolver typeResolver;

    public FactPopulatorFactory(Map<String, Object> populatedData, Map<String, Object> globalData, TypeResolver typeResolver) {
        this.populatedData = populatedData;
        this.globalData = globalData;
        this.typeResolver = typeResolver;
    }

    public Populator createFactPopulator(FactData fact) throws ClassNotFoundException {
        if (fact.isModify()) {
            return new ExistingFactPopulator(
                    populatedData,
                    typeResolver,
                    fact);
        } else {
            return new NewFactPopulator(
                    populatedData,
                    typeResolver,
                    fact);
        }
    }

    public Populator createGlobalFactPopulator(FactData fact) throws ClassNotFoundException {
        return new GlobalFactPopulator(
                populatedData,
                typeResolver,
                fact,
                globalData);
    }
}
