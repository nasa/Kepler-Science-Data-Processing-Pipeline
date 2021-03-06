/*
 * Copyright 2017 United States Government as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All Rights Reserved.
 * 
 * This file is available under the terms of the NASA Open Source Agreement
 * (NOSA). You should have received a copy of this agreement with the
 * Kepler source code; see the file NASA-OPEN-SOURCE-AGREEMENT.doc.
 * 
 * No Warranty: THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY
 * WARRANTY OF ANY KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY,
 * INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE
 * WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR FREEDOM FROM
 * INFRINGEMENT, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL BE ERROR
 * FREE, OR ANY WARRANTY THAT DOCUMENTATION, IF PROVIDED, WILL CONFORM
 * TO THE SUBJECT SOFTWARE. THIS AGREEMENT DOES NOT, IN ANY MANNER,
 * CONSTITUTE AN ENDORSEMENT BY GOVERNMENT AGENCY OR ANY PRIOR RECIPIENT
 * OF ANY RESULTS, RESULTING DESIGNS, HARDWARE, SOFTWARE PRODUCTS OR ANY
 * OTHER APPLICATIONS RESULTING FROM USE OF THE SUBJECT SOFTWARE.
 * FURTHER, GOVERNMENT AGENCY DISCLAIMS ALL WARRANTIES AND LIABILITIES
 * REGARDING THIRD-PARTY SOFTWARE, IF PRESENT IN THE ORIGINAL SOFTWARE,
 * AND DISTRIBUTES IT "AS IS."
 * 
 * Waiver and Indemnity: RECIPIENT AGREES TO WAIVE ANY AND ALL CLAIMS
 * AGAINST THE UNITED STATES GOVERNMENT, ITS CONTRACTORS AND
 * SUBCONTRACTORS, AS WELL AS ANY PRIOR RECIPIENT. IF RECIPIENT'S USE OF
 * THE SUBJECT SOFTWARE RESULTS IN ANY LIABILITIES, DEMANDS, DAMAGES,
 * EXPENSES OR LOSSES ARISING FROM SUCH USE, INCLUDING ANY DAMAGES FROM
 * PRODUCTS BASED ON, OR RESULTING FROM, RECIPIENT'S USE OF THE SUBJECT
 * SOFTWARE, RECIPIENT SHALL INDEMNIFY AND HOLD HARMLESS THE UNITED
 * STATES GOVERNMENT, ITS CONTRACTORS AND SUBCONTRACTORS, AS WELL AS ANY
 * PRIOR RECIPIENT, TO THE EXTENT PERMITTED BY LAW. RECIPIENT'S SOLE
 * REMEDY FOR ANY SUCH MATTER SHALL BE THE IMMEDIATE, UNILATERAL
 * TERMINATION OF THIS AGREEMENT.
 */

package gov.nasa.kepler.services.process;



import gov.nasa.kepler.services.messaging.MessageContext;
import gov.nasa.kepler.services.messaging.MessagingDestinations;
import gov.nasa.kepler.services.messaging.MessagingService;
import gov.nasa.kepler.services.messaging.MessagingServiceFactory;
import gov.nasa.spiffy.common.pi.PipelineException;

/**
 * Operations class for sending synchronous (request/reply) JMS
 * messages to {@link AbstractPipelineProcess}es for admin functions.
 * 
 * @author tklaus
 *
 */
public class PipelineProcessAdminOperations {

    public static final String PROCESS_PROP_NAME = "PipelineProcessName";

    private static final long REQUEST_TIMEOUT_MILLIS = 20000;

	private MessagingService messagingService;

    /**
     * @throws PipelineException 
	 * 
	 *
	 */
	public PipelineProcessAdminOperations() {
        messagingService = MessagingServiceFactory.getNonTransactedInstance();
	}
    
	public static String getProcessIdentifier(String processName, String processHost){
	    return processName + ":" + processHost;
	}

    /**
     * 
     * @param destProcessName
     * @param request
     * @return
     * @throws PipelineException
     */
    public PipelineProcessAdminResponse adminRequest(String destProcessName, String destProcessHost, PipelineProcessAdminRequest request) throws PipelineException{
        return adminRequest(destProcessName, destProcessHost, request, REQUEST_TIMEOUT_MILLIS);
    }

    /**
     * 
     * @param destProcessName
     * @param request
     * @return
     * @throws PipelineException
     */
    @SuppressWarnings("unchecked")
    public <T extends PipelineProcessAdminResponse> T adminRequest(String destProcessName, String destProcessHost, PipelineProcessAdminRequest request, long timeout) throws PipelineException{

        request.putJmsProperty(PROCESS_PROP_NAME, getProcessIdentifier(destProcessName, destProcessHost));
        
        MessageContext responseMsg = messagingService.request(MessagingDestinations.PIPELINE_ADMIN_DESTINATION, request, timeout);
        
        if(responseMsg == null){
            throw new PipelineException("Admin request: " + request + " timed out");
        }
        
        PipelineProcessAdminResponse response = (PipelineProcessAdminResponse) responseMsg.getPipelineMessage();
        
        return (T) response;
    }
}
