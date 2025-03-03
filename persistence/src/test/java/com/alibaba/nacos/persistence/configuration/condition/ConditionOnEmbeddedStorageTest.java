/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.persistence.configuration.condition;

import com.alibaba.nacos.persistence.configuration.DatasourceConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionOnEmbeddedStorageTest {
    
    @Mock
    ConditionContext context;
    
    @Mock
    AnnotatedTypeMetadata metadata;
    
    private ConditionOnEmbeddedStorage conditionOnEmbeddedStorage;
    
    @BeforeEach
    void init() {
        conditionOnEmbeddedStorage = new ConditionOnEmbeddedStorage();
    }
    
    @Test
    void testMatches() {
        MockedStatic<DatasourceConfiguration> mockedStatic = Mockito.mockStatic(DatasourceConfiguration.class);
        mockedStatic.when(DatasourceConfiguration::isEmbeddedStorage).thenReturn(true);
        assertTrue(conditionOnEmbeddedStorage.matches(context, metadata));
        
        mockedStatic.when(DatasourceConfiguration::isEmbeddedStorage).thenReturn(false);
        assertFalse(conditionOnEmbeddedStorage.matches(context, metadata));
        
        mockedStatic.close();
    }
    
}
