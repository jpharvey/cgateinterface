/*
 * CGateInterface - A library to allow interaction with Clipsal C-Gate.
 *
 * Copyright 2019 John Harvey <john.p.harvey@btinternet.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.daveoxley.cbus;

import java.util.HashMap;

import java.util.Map;

public class CGateTestThreadPool extends CGateThreadPool
{
    public CGateTestThreadPool()
    {}
	private Map<String, CGateThreadPoolExecutor> m_executorMap= new HashMap<String, CGateThreadPoolExecutor>();
    protected  CGateThreadPoolExecutor CreateExecutor(String name)
    {
	    CGateThreadPoolExecutor executor = m_executorMap.get(name);
	    if (executor != null)
	    {
		    return executor;
	    }
	    executor = new CGateTestThreadPoolExecutor();
	    m_executorMap.put(name, executor);
	    return executor;
    }
}
