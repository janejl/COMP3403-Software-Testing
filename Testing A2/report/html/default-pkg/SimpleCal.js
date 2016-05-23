var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":21,"id":0,"methods":[{"el":5,"sc":2,"sl":2},{"el":10,"sc":2,"sl":7},{"el":19,"sc":2,"sl":12}],"name":"SimpleCal","sl":1}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_63":{"methods":[{"sl":2}],"name":"testAdd","pass":false,"statements":[{"sl":3},{"sl":4}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = $jsonSrcFileLines
