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

package com.jerehao.devia.servlet.handler;

import com.jerehao.devia.logging.Logger;
import com.jerehao.devia.servlet.DeviaServletContext;
import com.jerehao.devia.servlet.HandlerExecutionChain;

/**
 * @author <a href="http://jerehao.com">jerehao</a>
 * @version 0.0.1 2018-01-08 9:17 jerehao
 */
public class RequestDispatcherHandler implements Handler {

    private static final Logger LOGGER = Logger.getLogger(RequestDispatcherHandler.class);

    @Override
    public void handle(DeviaServletContext servletContext, HandlerExecutionChain chain) {
        LOGGER.info("Entry RequestDispatcherHandler");
        chain.next();
    }
}
