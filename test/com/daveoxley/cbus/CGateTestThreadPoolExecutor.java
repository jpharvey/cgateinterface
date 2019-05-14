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
import com.workplacesystems.utilsj.threadpool.ThreadObjectFactory;
import com.workplacesystems.utilsj.threadpool.ThreadPool;
import com.workplacesystems.utilsj.threadpool.ThreadPoolCreator;
import com.workplacesystems.utilsj.threadpool.WorkerThread;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

public class CGateTestThreadPoolExecutor extends CGateThreadPoolExecutor
{
	private ThreadPool m_threadPool;
    public CGateTestThreadPoolExecutor()
    {
            ThreadPoolCreator tp_creator = new ThreadPoolCreator() {

                public ThreadObjectFactory getThreadObjectFactory() {
                    return new ThreadObjectFactory() {
                        @Override
                        public void initialiseThread(Thread thread)
                        {
                            thread.setName("Event");
                        }

                        @Override
                        public void activateThread(Thread thread)
                        {
                        }

                        @Override
                        public void passivateThread(Thread thread)
                        {
                        }
                    };
                }

                public Config getThreadPoolConfig() {
                    Config config = new Config();
                    config.maxActive = 10;
                    config.minIdle   = 2;
                    config.maxIdle   = 5;
                    config.testOnBorrow = false;
                    config.testOnReturn = true;
                    config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
                    config.maxWait = -1;
                    return config;
                }

                public String getThreadPoolName() {
                    return "EventPool";
                }
            };
            m_threadPool = new ThreadPool(tp_creator.getThreadObjectFactory(), tp_creator.getThreadPoolConfig());
    }
	protected void execute(Runnable runnable)
    {
	    try
	    {
		    WorkerThread callback_thread = (WorkerThread)m_threadPool.borrowObject();
		    callback_thread.execute(runnable, null);
	    }
	    catch (Exception e)
	    {
		    new CGateException(e);
	    }
    }
}
