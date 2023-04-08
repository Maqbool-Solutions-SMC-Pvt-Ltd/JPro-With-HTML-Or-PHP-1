FROM gitpod/workspace-full:latest

SHELL ["/bin/bash", "-c"]
RUN source "/home/gitpod/.sdkman/bin/sdkman-init.sh"  \
    && sdk install java 17.0.6.fx-zulu < /dev/null \
    && sdk default java 17.0.6.fx-zulu