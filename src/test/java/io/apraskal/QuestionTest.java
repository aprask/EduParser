package io.apraskal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import io.apraskal.model.Question;
import java.util.*;


public class QuestionTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public QuestionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( QuestionTest.class );
    }

    public void testQuestionMap()
    {
        HashMap<String, String[]> questionAndAnswers = new HashMap<>();
            questionAndAnswers.put("Who am I?", new String[]{"example"});
        HashMap<String, Double> questionAndWeights = new HashMap<>();
        Question instance = new Question(questionAndAnswers, questionAndWeights);
        HashMap<String, String[]> instanceMap = instance.getQuestionAndAnswers();
        String answerExample = instanceMap.get("Who am I?")[0];
        assertEquals("example", answerExample);
    }

}
