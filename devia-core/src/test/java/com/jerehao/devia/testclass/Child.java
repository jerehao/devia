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

package com.jerehao.devia.testclass;


import com.jerehao.devia.bean.annotation.Inject;
import com.jerehao.devia.bean.annotation.Named;

/**
 * @author <a href="http://jerehao.com">jerehao</a>
 * @version 0.0.1 2018-01-12 19:05 jerehao
 */
@Named()
public class Child {

    //@Inject
    private Mother mother;

    public Mother getMother() {
        return mother;
    }

    @Inject
    public Child(@Named("mother2") Mother mother) {
        this.mother = mother;
    }

    //@Inject
    public void setMother(@Named("mm") Mother mother) {
        this.mother = mother;
    }
}
