package reasoner.expressions;

import java.util.Set;

/**
 * GreaterThanOrEqualToExpression represents greater than or equal to in
 * description logic
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class GreaterThanOrEqualToExpression 
  extends NumberRestrictionExpression {

 /**
  * Returns GreaterThanOrEqualToExpression that represent the concept 
  * greater than or equal to
  * 
  * e.g. ">= 2.goto" where goto is the role and 2 is the count
  *
  * @param role    
  * @param concept the concept that the GreaterThanOrEqualToExpression 
  *                will represent
  * @return        the GreaterThanOrEqualToExpression that 
  *                represents the concept
  */
  public GreaterThanOrEqualToExpression(String role, int count) {
    super(role, count);
  }

 /**
  * Returns whether two expressions contradict each other.  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  @Override
  public boolean contradicts(Set<Expression> expressions) {
    int numberInstancesOfThisRole = getNumberInstancesOfThisRole(expressions);
    return count <= numberInstancesOfThisRole;
  }

 /**
  * Returns the role and count as a String
  *
  * @return the role and count as a String
  */
  @Override
  public String toString() {
    return ">=" + super.toString();
  }
}
