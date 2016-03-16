package reasoner.expressions;

import java.util.Set;

/**
 * LessThanOrEqualToExpression represents less than or equal to in
 * description logic
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class LessThanOrEqualToExpression extends NumberRestrictionExpression {

  /**
   * Constructs a new GreaterThanOrEqualToExpression object.
   *
   * e.g. "<= 2.goto" where goto is the role and 2 is the count
   *
   * @param role The role.
   * @param count The number restriction.
   */
  public LessThanOrEqualToExpression(String role, int count) {
    super(role, count);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contradicts(Set<Expression> expressions) {
    int numberInstancesOfThisRole = getNumberInstancesOfThisRole(expressions);
    return count >= numberInstancesOfThisRole;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "<=" + super.toString();
  }
}
