var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":43,"id":22,"methods":[{"el":16,"sc":2,"sl":13},{"el":21,"sc":2,"sl":18},{"el":26,"sc":2,"sl":23},{"el":31,"sc":2,"sl":28},{"el":37,"sc":2,"sl":33},{"el":42,"sc":2,"sl":39}],"name":"SimpleCalTest","sl":9}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_63":{"methods":[{"sl":33}],"name":"testAdd","pass":false,"statements":[{"sl":35},{"sl":36}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = $jsonSrcFileLines
