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

package gov.nasa.kepler.aft.gar;

import gov.nasa.kepler.common.SocEnvVars;
import gov.nasa.kepler.hibernate.dbservice.TransactionService;
import gov.nasa.kepler.hibernate.dbservice.TransactionServiceFactory;
import gov.nasa.kepler.pi.configuration.PipelineConfigurationOperations;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Creates an HSQLDB database with one {@code GAR_HUFFMAN_TABLE} and 2^17-1
 * {@code GAR_HUFFMAN_TABLE_ENTRIES} seeded by using an instance of
 * {@link gov.nasa.kepler.aft.gar.HuffmanImporterPipelineSeedData}.
 * 
 * @author Forrest Girouard
 * @author Bill Wohler
 */
public class HuffmanImporterTestDataGenerator extends HuffmanTestDataGenerator {

    private static final Log log = LogFactory.getLog(HuffmanImporterTestDataGenerator.class);

    private static final String HUFFMAN_IMPORTER_TRIGGER_NAME = "HUFFMAN_IMPORTER";

    @Override
    protected void createDatabaseContents() throws Exception {

        log.info(getLogName() + ": Importing pipeline configuration");
        new PipelineConfigurationOperations().importPipelineConfiguration(new File(
            SocEnvVars.getLocalDataDir(), AFT_PIPELINE_CONFIGURATION_ROOT
                + GENERATOR_NAME + ".xml"));
    }

    @Override
    protected void process() throws Exception {
        runPipeline(HUFFMAN_IMPORTER_TRIGGER_NAME);

        TransactionService transactionService = TransactionServiceFactory.getInstance();
        transactionService.beginTransaction();
        log.info(getLogName() + ": Modifying database objects");
        readyHuffmanTableForExport();
        transactionService.commitTransaction();
    }

    public static void main(String[] args) {
        try {
            new HuffmanImporterTestDataGenerator().generate();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.exit(0);
    }
}
