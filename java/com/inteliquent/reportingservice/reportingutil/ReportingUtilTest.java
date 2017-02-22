package com.inteliquent.reportingservice.reportingutil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created on 2/15/17.
 */
public class ReportingUtilTest {
    @Test
    public void getDataList() throws Exception {
        String jsonString =
            "{`hits`:{`total`:252,`max_score`:1,`hits`:[{`_index`:`sparkaggresults-2017-02-10`,`_type`:`result-24`,`_id`:`AVoqOfY-c6FDBcYLFzTk`,`_score`:null,`_source`:{`_formatVersion`:3,`_count`:15,`result`:{`avg`:{`gpa`:0.01},`max`:{`height`:10,`age`:91},`count`:{`gpa`:15,`age`:15},`min`:{`height`:1},`keys`:{`rule_name`:`Wash Trades`,`alert_category`:`System`,`alert_level`:`Warn`},`sum`:{`gpa`:0.15}},`key`:`Wash Trades.System.Warn`},`sort`:[`Wash Trades.System.Warn`]},{`_index`:`sparkaggresults-2017-02-10`,`_type`:`result-24`,`_id`:`AVoqOfWcc6FDBcYLFzTY`,`_score`:null,`_source`:{`_formatVersion`:3,`_count`:10,`result`:{`avg`:{`gpa`:0.02},`max`:{`height`:10,`age`:100},`count`:{`gpa`:10,`age`:10},`min`:{`height`:1},`keys`:{`rule_name`:`Wash Trades`,`alert_category`:`System`,`alert_level`:`Info`},`sum`:{`gpa`:0.2}},`key`:`Wash Trades.System.Info`},`sort`:[`Wash Trades.System.Info`]},{`_index`:`sparkaggresults-2017-02-10`,`_type`:`result-24`,`_id`:`AVoqOfY-c6FDBcYLFzTd`,`_score`:null,`_source`:{`_formatVersion`:3,`_count`:12,`result`:{`avg`:{`gpa`:0.04},`max`:{`height`:10,`age`:95},`count`:{`gpa`:12,`age`:12},`min`:{`height`:1},`keys`:{`rule_name`:`Wash Trades`,`alert_category`:`System`,`alert_level`:`Error`},`sum`:{`gpa`:0.48000000000000004}},`key`:`Wash Trades.System.Error`},`sort`:[`Wash Trades.System.Error`]}]  }}"
            .replace("`", "\"");

        String expectedResultString =
                "{`dataItems`:[{`alert_category`:`System`,`alert_level`:`Warn`,`rule_name`:`Wash Trades`,`Average gpa`:`0.01`,`Count age`:`15`,`Count gpa`:`15`,`Max age`:`91`,`Max height`:`10`,`Min height`:`1`,`Sum gpa`:`0.15`},{`alert_category`:`System`,`alert_level`:`Info`,`rule_name`:`Wash Trades`,`Average gpa`:`0.02`,`Count age`:`10`,`Count gpa`:`10`,`Max age`:`100`,`Max height`:`10`,`Min height`:`1`,`Sum gpa`:`0.20`},{`alert_category`:`System`,`alert_level`:`Error`,`rule_name`:`Wash Trades`,`Average gpa`:`0.04`,`Count age`:`12`,`Count gpa`:`12`,`Max age`:`95`,`Max height`:`10`,`Min height`:`1`,`Sum gpa`:`0.48`}]}"
                .replace("`", "\"");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonString);

        JsonNode expectedResultJson = mapper.readTree(expectedResultString);

        JsonNode resultNode = ReportingUtil.getDataList(node);
        assertTrue("getDataList parsing error", resultNode != null);
        assertTrue(resultNode.get("dataItems") != null);

        JsonNode expectedDataItems = expectedResultJson.get("dataItems");
        JsonNode actualDataItems = resultNode.get("dataItems");

        int i = 0;
        for (JsonNode expectedNode : expectedDataItems) {
            Iterator<Map.Entry<String, JsonNode>> expectedFields = expectedNode.fields();
            JsonNode actualNode = actualDataItems.get(i);

            while(expectedFields.hasNext()) {
                Map.Entry<String, JsonNode> expectedKeyVal = expectedFields.next();
                String key = expectedKeyVal.getKey();
                assertTrue(actualNode.has(key));

                String expectedValue = expectedKeyVal.getValue().asText();
                String actualValue = actualNode.get(key).asText();
                assertTrue("Actual and expected values do not match: Actual=" + actualValue + ", expected=" + expectedValue,
                        actualValue.equals(expectedValue));
            }

            i++;
        }
    }

}