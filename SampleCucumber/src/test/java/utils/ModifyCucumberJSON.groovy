import groovy.json.JsonSlurper
import groovy.json.JsonOutput

fileC = System.getProperty("user.dir") + "\\target\\cucumber.json"
println("Original Cucumber JSON PATH: " + fileC)

def jsonSlurper = new JsonSlurper()
def jsonfile = jsonSlurper.parse(new File(fileC))

for(step in jsonfile[0].elements[0].steps) {
  if(step.output.join(",").contains("error_message"))
  	step.result.status = "failed"
}

fileCF = System.getProperty("user.dir") + "\\target\\cucumberfinal.json"
def json = JsonOutput.toJson(jsonfile)
new File(fileCF).write(json)
println("Updated Cucumber JSON PATH: " + fileCF)

//Use this as a PostStep - Execute Groovy Script
//Need - In cucumber report logging, if a validation fails, would not validate further; so we can just log error details instead directly using assert statement, 
//and update the step status in the JSON report file. 
//The Cucumber Reporter plug-in will be instructed to use this modified file, so your report looks as expected (ex. first step may be FAILED but next step PASSED)
