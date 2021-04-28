package hardcore.service;

import hardcore.model.Engine;

public class EngineCreator {

    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.instances.amount";
    public static final String TESTDATA_BASE_TYPE = "testdata.base.type";
    public static final String TESTDATA_LOCATION = "testdata.location";
    public static final String TESTDATA_COMMITTED_USAGE = "testdata.committed.usage";

    public static Engine createEngineFromProperties() {
        return new Engine(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES),
                          TestDataReader.getTestData(TESTDATA_BASE_TYPE),
                          TestDataReader.getTestData(TESTDATA_LOCATION),
                          TestDataReader.getTestData(TESTDATA_COMMITTED_USAGE));
    }
}