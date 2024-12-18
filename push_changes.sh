#!/bin/bash
cd /users/harikrishnan/clj_projects/nxt-server
git add src/main/nxt/tools/data_model/relationships.clj
git add src/main/nxt/tools/data_model/requirements.clj
git add src/test/nxt/tools/data_model/requirements_test.clj
git commit -m "Add data model implementation for tenant-user requirements

- Add relationships module for relationship patterns
- Add requirements module for entity definitions
- Add test module with initial tests
- Implement tenant and user entities with relationships
- Follow boolean naming conventions with ? suffix"
git push origin main