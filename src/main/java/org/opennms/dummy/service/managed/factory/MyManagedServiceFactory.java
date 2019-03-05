/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2019-2019 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2019 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.dummy.service.managed.factory;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyManagedServiceFactory implements ManagedServiceFactory {

    private final Logger LOG = LoggerFactory.getLogger(MyManagedServiceFactory.class);

    private final Set<String> pids = new HashSet<>();

    public String getName() {
        return getClass().getSimpleName();
    }

    public void updated(String pid, Dictionary<String, ?> properties) throws ConfigurationException {
        if (pids.contains(pid)) {
            LOG.info("PID {} updated", pid);
        } else {
            pids.add(pid);
            LOG.info ("PID {} created", pid);
        }
    }

    public void deleted(String pid) {
        pids.remove(pid);
        LOG.info("PID {} deleted", pid);
    }
}
