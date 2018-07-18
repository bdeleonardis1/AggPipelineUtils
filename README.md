# Agg Pipeline Utils
## Description
Agg Pipeline Utils is a tool that accepts an aggregation pipeline with additional functionality and outputs a new pipeline that can actually run on MongoDB. All MQL Agg that is valid on MongoDB is valid in this tool as well but this tool will also allow you to write agg pipelines with additional functions (documented below).

## Motivation
Some simple things are very difficult to do in the agg pipeline. Examples include changing a string to a character array or turning the string version of a number into an integer. This can make writing pipelines quite difficult (and completely unreadable) for certain functionality (like changing number bases).  This program allows users to write queries faster. Realistically these functions aren't applicable in that many uses cases, but it was a fun Skunkworks challenge!

## Added Functions
`$toCharArray`:   
input: string  
output: array of strings  
usage example: `{$toCharArray: "hello'} --> ['h', 'e', 'l', 'l', 'o']`

`$parseInt`:  
input: string  
output: integer  
usage example: `{$parseInt: "123"} --> 123`

`$toAscii`:  
input: string  
output: integer  
usage example: `{$toAscii: "a"} --> 97`

`$switchToCond`:  
input: switch structure  
output: cond structure that does the exact same thing as the original switch (this does not add functionality, it simply makes writing 3.2 compliant MQL easier)

## Example Generated Pipeline
```javascript
[{
	$project: {
		integer: {
			$parseInt: "$num"
		}
	}
}]
```
Gets translated to:
```
javascript
[
  {
    "$project": {
      "integer": {
        "$reduce": {
          "input": {
            "$map": {
              "in": {
                "$switch": {
                  "branches": [
                    {
                      "case": {
                        "$eq": [
                          "0",
                          "$$this"
                        ]
                      },
                      "then": 0
                    },
                    {
                      "case": {
                        "$eq": [
                          "1",
                          "$$this"
                        ]
                      },
                      "then": 1
                    },
                    {
                      "case": {
                        "$eq": [
                          "2",
                          "$$this"
                        ]
                      },
                      "then": 2
                    },
                    {
                      "case": {
                        "$eq": [
                          "3",
                          "$$this"
                        ]
                      },
                      "then": 3
                    },
                    {
                      "case": {
                        "$eq": [
                          "4",
                          "$$this"
                        ]
                      },
                      "then": 4
                    },
                    {
                      "case": {
                        "$eq": [
                          "5",
                          "$$this"
                        ]
                      },
                      "then": 5
                    },
                    {
                      "case": {
                        "$eq": [
                          "6",
                          "$$this"
                        ]
                      },
                      "then": 6
                    },
                    {
                      "case": {
                        "$eq": [
                          "7",
                          "$$this"
                        ]
                      },
                      "then": 7
                    },
                    {
                      "case": {
                        "$eq": [
                          "8",
                          "$$this"
                        ]
                      },
                      "then": 8
                    },
                    {
                      "case": {
                        "$eq": [
                          "9",
                          "$$this"
                        ]
                      },
                      "then": 9
                    }
                  ]
                }
              },
              "input": {
                "$map": {
                  "in": {
                    "$substr": [
                      "$num",
                      "$$this",
                      1
                    ]
                  },
                  "input": {
                    "$range": [
                      0,
                      {
                        "$strLenCP": "$num"
                      },
                      1
                    ]
                  }
                }
              }
            }
          },
          "initialValue": 0,
          "in": {
            "$sum": [
              {
                "$multiply": [
                  "$$value",
                  10
                ]
              },
              "$$this"
            ]
          }
        }
      }
    }
  }
]":5},{"case":{"$eq":["6","$$this"]},"then":6},{"case":{"$eq":["7","$$this"]},"then":7},{"case":{"$eq":["8","$$this"]},"then":8},{"case":{"$eq":["9","$$this"]},"then":9}]}},"input":{"$map":{"in":{"$substr":["$num","$$this",1]},"input":{"$range":[0,{"$strLenCP":"$num"},1]}}}}},"initialValue":0,"in":{"$sum":[{"$multiply":["$$value",10]},"$$this"]}}}}}]
```

```
[{
	$project: {
		charArrays: {
			$toCharArray: "$num"
			}
		}
	}
]
```
 gets translated to:
```
[
  {
    "$project": {
      "charArrays": {
        "$map": {
          "in": {
            "$substr": [
              "$num",
              "$$this",
              1
            ]
          },
          "input": {
            "$range": [
              0,
              {
                "$strLenCP": "$num"
              },
              1
            ]
          }
        }
      }
    }
  }
]
```

## Download
Clone this repo: `git clone https://github.com/bdeleonardis1/AggPipelineUtils.git`  


## Build
Navigate to repo, and run `javac Main.java`

## Run
`java Main`  
Write/Paste your query into the prompt. 



