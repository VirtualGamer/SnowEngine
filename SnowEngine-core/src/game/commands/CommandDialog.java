/**
 * Copyright (c) 2016 Mark Rienstra
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package game.commands;

import javax.swing.JOptionPane;

public final class CommandDialog
{
    public CommandDialog()
    {
        String command = JOptionPane.showInputDialog(null, "Enter a command", "Spiral Knights", JOptionPane.PLAIN_MESSAGE);
        
        Command cmd = CommandDialog.getCommand(command);
        if (cmd instanceof ScoreCommand)
        {
            ((ScoreCommand) cmd).parse(command);
        }
        else if (cmd instanceof HealthCommand)
        {
            ((HealthCommand) cmd).parse(command);
        }
        else if (cmd instanceof VolumeCommand)
        {
            ((VolumeCommand) cmd).parse(command);
        }
    }
    
    private static Command getCommand(String command)
    {
        if (command.startsWith("score"))
        {
            return new ScoreCommand();
        }
        else if (command.startsWith("health"))
        {
            return new HealthCommand();
        }
        else if (command.startsWith("volume"))
        {
            return new VolumeCommand();
        }
        return null;
    }
}
