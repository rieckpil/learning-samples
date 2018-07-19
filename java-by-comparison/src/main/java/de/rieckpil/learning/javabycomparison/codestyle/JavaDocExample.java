package de.rieckpil.learning.javabycomparison.codestyle;

public class JavaDocExample {

    public static void main(String[] args) {

        JavaDocExample javaDocExample = new JavaDocExample();

        javaDocExample.someMethod(19);

    }

    /**
     * The central business logic for verifying the age of a user.
     * <p>
     *     <ul>
     *         <li>
     *             look at the following example {@link FormattingStrings}
     *         </li>
     *     </ul>
     * </p>
     *
     * <pre>
     *     someMethod(18); -> returns true
     *     someMethod(12); -> return false
     * </pre>
     *
     *
     * @param age the age of the user to verify
     */
    public void someMethod(int age) {

    }
}
