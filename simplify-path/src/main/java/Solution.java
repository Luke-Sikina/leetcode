import java.util.ArrayList;
import java.util.List;

class Solution {
    public String simplifyPath(String path) {
        List<String> processedNodes = new ArrayList<>();
        for (String node : path.split("/")) {
            switch (node) {
                case "":
                case ".":
                    continue;
                case "..":
                    if (!processedNodes.isEmpty()) {
                        // go back 1 dir
                        processedNodes.remove(processedNodes.size() - 1);
                    }
                    continue;
                default:
                    processedNodes.add(node);
            }
        }

        return "/" + String.join("/", processedNodes);
    }
}