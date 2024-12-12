#!/bin/bash
set -e

# Clean previous coverage reports
rm -rf target/coverage

# Run coverage analysis
clojure -M:coverage

# Print coverage summary
echo "Coverage report generated in target/coverage/index.html"

# If running in CI with codecov, upload the report
if [ -n "$CI" ] && [ -n "$CODECOV_TOKEN" ]; then
  bash <(curl -s https://codecov.io/bash)
fi