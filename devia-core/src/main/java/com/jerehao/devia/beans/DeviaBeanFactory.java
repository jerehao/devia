/*
 * Copyright (c) 2018, jerehao.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jerehao.devia.beans;

import com.jerehao.devia.beans.build.DeviaBeanBuilder;
import com.jerehao.devia.logging.Logger;

/**
 * @author <a href="http://jerehao.com">jerehao</a>
 * @version 0.0.1 2018-01-09 16:28 jerehao
 */

public class DeviaBeanFactory extends AbstractBeanFactory {

    private static final Logger LOGGER = Logger.getLogger(DeviaBeanFactory.class);

    private static final BeanFactory instance;

    static {
        instance = new DeviaBeanFactory();
    }

    public static BeanFactory getInstance() {
        return instance;
    }

    private DeviaBeanFactory() {
        super.setBeanBuilder(new DeviaBeanBuilder(this));
    }
}
