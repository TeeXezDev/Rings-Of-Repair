Mandatory Review & Fix Policy

Critical Requirement

For EVERY task involving:

- Code modifications
- New features
- Bug fixes
- Refactoring
- Configuration changes
- Dependency updates
- Security changes
- File creation or deletion

A complete review is mandatory before the task can be considered finished.

---

Required Workflow

PLAN

→ IMPLEMENT

→ REVIEW (HIGH)

→ FIND ISSUES

→ FIX ISSUES

→ REVIEW AGAIN

→ FIX AGAIN

→ REPEAT UNTIL NO ISSUES ARE FOUND

→ FINAL RESULT

---

High Review Requirements

Review all modified files and related files for:

Functional Issues

- Broken logic
- Missing implementations
- Incorrect conditions
- Edge cases
- Unhandled exceptions

Code Quality Issues

- Dead code
- Duplicate code
- Unused imports
- Unused variables
- Incorrect naming
- Poor structure

Consistency Issues

- Inconsistent APIs
- Inconsistent architecture
- Inconsistent data models
- Inconsistent coding style
- Inconsistent naming conventions

Integration Issues

- Broken dependencies
- Incorrect imports
- Incorrect exports
- Missing registrations
- Invalid references

Build Issues

- Syntax errors
- Type errors
- Lint errors
- Compilation errors
- Runtime startup errors

Security Issues

- Unsafe input handling
- Missing validation
- Sensitive data exposure
- Permission issues

---

Mandatory Fix Rule

If any issue is discovered:

1. Fix the issue immediately.
2. Re-check affected files.
3. Re-run review.
4. Continue until no additional issues are found.

Do not stop after reporting problems.

The objective is:

FIND → FIX → VERIFY

not

FIND → REPORT

---

Completion Criteria

A task may only be considered complete when:

- No syntax errors remain.
- No build errors remain.
- No obvious logic errors remain.
- No inconsistent code remains.
- No broken imports or references remain.
- No unfinished implementations remain.
- No review findings remain unfixed.

---

Final Response Format

Before finishing, provide:

Review Summary

- Files reviewed
- Issues found
- Issues fixed
- Validation performed
- Remaining risks (if any)

Never skip review.
Never stop at issue discovery.
Always attempt remediation.
Continue review/fix cycles until no further problems are found.
