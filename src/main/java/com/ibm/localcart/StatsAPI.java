/*******************************************************************************
 * Copyright (c) 2017 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 

package com.ibm.localcart;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;

/**
 * @author dtaieb
 *
 */

@ApplicationPath("api")
@Path("/")
public class StatsAPI extends Application{
	@GET
    @Path("/stats")
    @Produces({"application/json"})
	public String getStats(){
		return new Gson().toJson( DataStream.getInstance().stats );
	}
	
	@GET
    @Path("/config")
    @Produces({"application/json"})
	public String getConfig() throws Throwable{
		return new Gson().toJson( MessageHubConfig.getInstance().getConfig() );
	}
	
	public static class EPS{
		public int eps;
	}
	
	@POST
	@Path("/eps")
	@Consumes({"application/json"})
	public void setEps(EPS data){
		DataStream.setEventsPerSeconds( data.eps );
	}
}
