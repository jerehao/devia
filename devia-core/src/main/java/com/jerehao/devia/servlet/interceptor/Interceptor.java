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

package com.jerehao.devia.servlet.interceptor;

import com.jerehao.devia.servlet.DeviaServletContext;

/**
 * @author <a href="http://jerehao.com">jerehao</a>
 * @version 0.0.1 2018-01-16 9:04 jerehao
 */
public interface Interceptor {
    /**
     * 增加配置文件中interceptors的读取
     *  形如：
     *      <interceptors>
     *          <interceptor class="">
     *              <path></path>
     *          </interceptor>
     *      </interceptors>
     * 或直接使用注解配置
     */

    boolean preHandler(DeviaServletContext context, Object o);

    void postHandler(DeviaServletContext context, Object o);

}