import java.util.ArrayList;
import java.util.List;

class Solution {
    public String simplifyPath(String path) {
        List<String> processedNodes = new ArrayList<>();
        for (String node : path.split("/")) {
            if (node.isEmpty() || ".".equals(node)) {
                continue;
            } else if ("..".equals(node)) {
                if (!processedNodes.isEmpty()) {
                    // go back 1 dir
                    processedNodes.remove(processedNodes.size() - 1);
                }
            } else {
                processedNodes.add(node);
            }
        }

        return "/" + String.join("/", processedNodes);
    }
}