var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":20,"id":70,"methods":[{"el":6,"sc":2,"sl":4},{"el":10,"sc":2,"sl":8},{"el":14,"sc":2,"sl":12},{"el":18,"sc":2,"sl":16}],"name":"Lemon","sl":2}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_2":{"methods":[{"sl":4},{"sl":8}],"name":"testMakeOrder","pass":true,"statements":[{"sl":5},{"sl":9}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [2], [2], [], [], [2], [2], [], [], [], [], [], [], [], [], [], [], []]
