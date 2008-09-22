/**
 *  CGateInterface - A library to allow interaction with Clipsal C-Gate.
 *  Copyright (C) 2008  Dave Oxley <dave@daveoxley.co.uk>.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.daveoxley.cbus;

/**
 *
 * @author Dave Oxley <dave@daveoxley.co.uk>
 */
public class Group extends Unit
{
    private Application application;

    Group(Application application, String cgate_response)
    {
        super(application.getNetwork(), cgate_response);
        this.application = application;
    }

    /**
     * Issue a <code>on //PROJECT/NET_ID/GROUP_ID</code> to the C-Gate server.
     * 
     * @see <a href="http://www.clipsal.com/cis/downloads/Toolkit/CGateServerGuide_1_0.pdf">
     *      <i>C-Gate Server Guide 4.3.79</i></a>
     * @param cgate_session The C-Gate session
     * @throws CGateException
     */
    public void on(CGateSession cgate_session) throws CGateException
    {
        String address = "//" + getNetwork().getProjectName() + "/" + getNetwork().getNetworkID() + "/" + getUnitID();
        handle200Response(cgate_session.sendCommand("on " + address));
    }

    /**
     * Issue a <code>off //PROJECT/NET_ID/GROUP_ID</code> to the C-Gate server.
     * 
     * @see <a href="http://www.clipsal.com/cis/downloads/Toolkit/CGateServerGuide_1_0.pdf">
     *      <i>C-Gate Server Guide 4.3.77</i></a>
     * @param cgate_session The C-Gate session
     * @throws CGateException
     */
    public void off(CGateSession cgate_session) throws CGateException
    {
        String address = "//" + getNetwork().getProjectName() + "/" + getNetwork().getNetworkID() + "/" + getUnitID();
        handle200Response(cgate_session.sendCommand("off " + address));
    }

    /**
     * Issue a <code>ramp //PROJECT/NET_ID/GROUP_ID</code> to the C-Gate server.
     * 
     * @see <a href="http://www.clipsal.com/cis/downloads/Toolkit/CGateServerGuide_1_0.pdf">
     *      <i>C-Gate Server Guide 4.3.100</i></a>
     * @param cgate_session The C-Gate session
     * @param level
     * @param seconds
     * @throws CGateException
     */
    public void ramp(CGateSession cgate_session, int level, int seconds) throws CGateException
    {
        String address = "//" + getNetwork().getProjectName() + "/" + getNetwork().getNetworkID() + "/" + getUnitID();
        handle200Response(cgate_session.sendCommand("ramp " + address + " " + level + " " + seconds + "s"));
    }
}
