package de.zauberstuhl.encoapp;

/**
 * Copyright (C) 2013 Lukas Matt <lukas@zauberstuhl.de>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

public class User {
	public String jid;
	public String name = null;
	public boolean online = false;
	
    public User() {
        super();
    }
   
    public User(String jid) {
        super();
        this.jid = jid;
    }
    
    public User(String jid, String name) {
        super();
        this.jid = jid;
        this.name = name;
    }
    
    public User(String jid, boolean online) {
        super();
        this.jid = jid;
        this.online = online;
    }
    
    public User(String jid, String name, boolean online) {
        super();
        this.jid = jid;
        this.name = name;
        this.online = online;
    }
}
