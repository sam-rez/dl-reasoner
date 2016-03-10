package reasoner.expressions;

import java.util.HashSet;
import java.util.Set;

/**
 * ExistentialExpression is in the DL dialect that connects a "Role" with a "Concept".
 *
 * Here Role is defined as a structure. The way we model "Concept" is a set
 * of Expressions, and this makes it easier to determine if contradictions exist.
 *
 * Example usage:
 *
 * ExistentialExpression goToBar = new ExistentialExpression("goTo", new ConceptExpression("BAR"));
 *
 * If for instance, we have BAR equivalent PUB, we can use {@method addConcept} to add PUB to the list of expressions.
 */
public class ExistentialExpression extends Expression {
  public String role;
  public HashSet<Expression> expressions;

  public ExistentialExpression(String role, Expression concept) {
    this.role = role;
    expressions = new HashSet<>();
    expressions.add(concept);
  }

  public void addConcept(Expression concept) {
    expressions.add(concept);
  }

  @Override
  public boolean contradicts(Expression other) {
    if (other instanceof ExistentialExpression) {
      ExistentialExpression o = (ExistentialExpression) other;
      if (o.role.equals(role)) {
        for (Expression e : expressions) {
          for (Expression e2 : o.expressions) {
            if (e.contradicts(e2)) {
              return true;
            }
          }
        }
      }
    } else if (other instanceof NotExpression) {
      return other.contradicts(this);
    }
    return false;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ExistentialExpression) {
      ExistentialExpression o = (ExistentialExpression) other;
      if (o.role.equals(this.role)) {
        HashSet<Expression> combined = new HashSet<>();
        combined.addAll(this.expressions);
        combined.removeAll(o.expressions);
        return combined.isEmpty();
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hashCode = role.hashCode();
    for (Expression concept : expressions) {
      hashCode += concept.hashCode();
    }
    return hashCode;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(role + "." + "[");
    for (Expression concept : expressions) {
      sb.append(concept);
      sb.append(",");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    return sb.toString();
  }

  public void addConcepts(Set<Expression> expressions) {
    this.expressions.addAll(expressions);
  }
}
